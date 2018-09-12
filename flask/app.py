
#pip install flask
from flask import Flask
app = Flask(__name__)


@app.route('/')
def hello():
    return "Hello World!"

@app.route('/<name>')
def hello_name(name):
    return "Hello {}!".format(name)


if __name__ == '__main__':
    #app.run()
    #app.run(debug=True)
    #app.run(debug=True,port=80)

#python app.py
#http://localhost:5000
#http://localhost:5000/mike