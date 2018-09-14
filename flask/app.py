from flask import Flask, request, jsonify
from sklearn.externals import joblib
from models import iris_model_load

app = Flask(__name__)

HTTP_BAD_REQUEST = 400


@app.route('/train', methods=['POST'])
def train():
    return "training"

@app.route('/predict', methods=['POST'])
def predict():
    request_data = request.get_json()
    print(request_data)
    sepal_length = request_data["sepal_length"]
    sepal_width = request_data["sepal_width"]
    petal_length = request_data["petal_length"]
    petal_width = request_data["petal_width"]

    # Reject request that have bad or missing values.
    if (sepal_length is None or sepal_width is None
        or petal_length is None or petal_width is None):
        # Provide the caller with feedback on why the record is unscorable.
        message = ('Record cannot be scored because of '
                   'missing or unacceptable values. '
                   'All values must be present and of type float.')
        response = jsonify(status='error',
                           error_message=message)
        # Sets the status code to 400
        response.status_code = HTTP_BAD_REQUEST
        return response

    features = [[sepal_length, sepal_width, petal_length, petal_width]]

    try:
        label = iris_model_load.load_model(features)
    except Exception as err:
        message = ('Failed to score the model. Exception: {}'.format(err))
        response = jsonify(status='error', error_message=message)
        response.status_code = HTTP_BAD_REQUEST
        return response

    label = iris_model_load.load_model(features)

    return jsonify(status='complete', label=label)


if __name__ == '__main__':
    app.run(debug=True)
