# Description

A basic poc of

    minikube , 
    kubernates , 
    istio 

working in virtual box running in local system.

# Setup

1.  Install virtual box in your system first 

        sudo apt install virtualbox virtualbox-ext-pack

2.  Download minikube

        wget https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64

        chmod +x minikube-linux-amd64

        sudo mv minikube-linux-amd64 /usr/local/bin/minikube

3. install kubectl

        curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -

        echo "deb http://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list

        sudo apt update

        sudo apt -y install kubectl

4.  Start minikube

        minikube start --memory=16384 --cpus=4 --kubernetes-version=v1.15.0

5.  some basic commands

        to check the cluster info
        
                kubectl cluster-info
        
        minikube config file can be found under
                ~/.minikube/machines/minikube/config.json

        to stop/delete  a running cluster

                minikube stop 

                minikube delete


6.  Enable kubernates DashBoard

        minikube addons list
        minikube dashboard

        to get dashboard url 

                minikube dashboard --url


    To verify memory usage of minikube 

            minikube ssh
            top

    If we want minikube to provide a load balancer 

            sudo minikube tunnel 

    sometimes minikube does not properly clean tunnel 

        sudo minikube tunnel --cleanup

7.  Installing Istio

        https://istio.io/docs/setup/kubernetes/#downloading-the-release

        follow the docs and after downloading the binaries move it under /usr/local/bin


###  Now in order to check the deployment we can use helm and tiller based configuration or without them ..

### first we will see without them , so installation of helm and tiller will be done later

Installation steps Istio in kubernates cluster:

        go to instio installation directory and install all istio crds (custom resource definitions )
        
        for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl apply -f $i; done

        for now we will install permissive profile to support both new and old deployments:

        kubectl apply -f install/kubernetes/istio-demo.yaml

        verify the installation

        kubectl get svc -n istio-system
        kubectl get pods -n istio-system

                3 will show as completed rest as running
        


8. Deploying microservices and testing 

        https://istio.io/docs/examples/bookinfo/


        https://kubernetes.io/docs/tasks/administer-cluster/namespaces/
        

        check all the namespaces
                kubectl get namespace
        to create a namespace
                kubectl create namespace XYZ
        to delete 
                kubectl delete namespace XYZ
        to get labels
                kubectl get namespaces --show-labels

        label the namespace that will host application with istio-injection=enabled
                kubectl label namespace demo istio-injection=enabled
        
        Deploy your application using the kubectl command
                kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml -n demo

        Confirm all services and pods are correctly defined and running:
                kubectl get services
                kubectl get pods
        
        To confirm that the Bookinfo application is running, send a request to it by a curl command from some pod, for example from ratings
                kubectl exec -it $(kubectl get pod -l app=ratings -o jsonpath='{.items[0].metadata.name}') -c ratings -- curl productpage:9080/productpage | grep -o "<title>.*</title>"

        for a specific namespace 

        kubectl -n demo exec -it $(kubectl get pod -n demo -l app=ratings -o jsonpath='{.items[0].metadata.name}') -c ratings -- curl productpage:9080/productpage | grep -o "<title>.*</title>"
        
        you will get the output 
                <title>Simple Bookstore App</title>


9.  Now we will verify istio installation by several istio tasks

        collecting logs:
        
        Apply a YAML file with configuration for the new log stream that Istio will generate and collect automatically.

        kubectl apply -f samples/bookinfo/telemetry/log-entry.yaml

        Send traffic to the sample application.

        kubectl -n demo exec -it $(kubectl get pod -n demo -l app=ratings -o jsonpath='{.items[0].metadata.name}') -c ratings -- curl productpage:9080/productpage | grep -o "<title>.*</title>"

        Verify that the log stream has been created and is being populated for requests.

        kubectl logs -n istio-system -l istio-mixer-type=telemetry -c mixer | grep "newlog"

Now that application is up and working , we need an ingress controller to make it accessible from outside cluster from browser
        An Istio-Gateway is used for this.



---



8.  Install a heml client

        Download the desired version from git 
        unpack it tar -zxvf helm-v2.0.0-linux-amd64.tgz
        move helm binary from /linux-amd64/helm /usr/local/bin/helm

9.  Installing tiller

            heml init

        it will connect to kubernates current context cluster and will install tiller there .

        verify the tiller installation
             kubectl get pods -n kube-system

        https://github.com/helm/helm/blob/master/docs/install.md

# Configuration

run this command to use helm and tiller for further operations

    helm repo add istio.io https://storage.googleapis.com/istio-release/releases/1.2.2/charts/

