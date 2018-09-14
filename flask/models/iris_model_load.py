from sklearn.externals import joblib
import os


def load_model(features):
    #cwd = os.getcwd()
    #print(cwd)
    #MODEL = joblib.load('path')
    MODEL = joblib.load('path')
    MODEL_LABELS = ['setosa', 'versicolor', 'virginica']
    # Use the model to predict the class
    label_index = MODEL.predict(features)
    # Retrieve the iris name th at is associated with the predicted class
    label = MODEL_LABELS[label_index[0]]
    return label

#print(load_model([[1,2,3,4]]))

