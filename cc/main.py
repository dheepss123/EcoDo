from fastapi import FastAPI, File, UploadFile, HTTPException
import tensorflow as tf
from tensorflow.keras.preprocessing import image
from tensorflow.keras.applications.mobilenet_v2 import preprocess_input
import numpy as np
from PIL import Image
import io
import os

app = FastAPI()
db_settings = {
    "host": "",
    "user": "root",
    "password": "akukamu",
    "database": "ecodo",
}
model_path = os.path.join(os.path.dirname(__file__), 'SavedModel')
model = tf.saved_model.load(model_path)

class_mapping = {'cardboard': 0, 'glass': 1, 'metal': 2, 'organic': 3, 'paper': 4, 'plastic': 5}

def predict_image(model, img):

    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0)
    img_array = preprocess_input(img_array)

    predictions = model(img_array)
    predicted_class_index = np.argmax(predictions)
    predicted_class = list(class_mapping.keys())[list(class_mapping.values()).index(predicted_class_index)]
    confidence = predictions[0][predicted_class_index]

    return predicted_class, confidence

@app.post("/make-predictions")
async def make_predictions(file: UploadFile = File(...)):
    try:
        content = await file.read()
        image_obj = Image.open(io.BytesIO(content))
        image_obj = image_obj.resize((224, 224))

        predicted_class, confidence = predict_image(model, image_obj)

        response_data = {
            "error": False,
            "message": "get data success",
            "data": {
                "predicted_class": predicted_class,
                "confidence": float(confidence),
                "description": description,
                "image_url": image_url,
                "video_url": video_url,
            }
        }
        return response_data

    except Exception as e:

        response_data = {
            "error": True,
            "message": "get data failed",
            "data": None
        }
        raise HTTPException(status_code=500, detail=response_data)
