sudo easy_install pip
sudo pip install --upgrade virtualenv
virtualenv --system-site-packages -p python3 tensorflow # for Python 3.n
source ~/tensorflow/bin/activate  
#source ~/tensorflow/bin/activate.csh 
easy_install -U pip
pip3 install --upgrade tensorflow
pip3 install --upgrade
pip3 install --upgrade https://storage.googleapis.com/tensorflow/mac/cpu/tensorflow-1.3.0-py2-none-any.whl
#deactivate
rm -r ~/tensorflow 
