# Description

A basic poc of

    minikube , 
    kubernates , 
    istio 

working in virtual box running in local system.

# Steps

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

        minikube start

5.  some basic commands

        to check the cluster info
        
                kubectl ccluster-info
        
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

