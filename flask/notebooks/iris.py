from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.externals import joblib

# Grab the dataset from scikit-learn
X, y = datasets.load_iris(return_X_y=True)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3,
                                                    random_state=42)
# Build and train the model
model = RandomForestClassifier(random_state=101)
model.fit(X_train, y_train)
from sklearn.metrics import accuracy_score
pred = model.predict(X_test)
print("accuracy_score on the test set is: {:.2}".format(accuracy_score(y_test,pred)))
model_filename = '../models/pickles/iris-rf-v1.0.pkl'
print("Saving model to {}...".format(model_filename))
joblib.dump(model, model_filename)
