import io
from fastapi import FastAPI, UploadFile, File
from PIL import Image
import numpy as np
import mysql.connector
import tensorflow as tf
import methode

app = FastAPI()

class_mapping = {'cardboard': 0, 'glass': 1, 'metal': 2, 'organic': 3, 'paper': 4, 'plastic': 5}

# Database connection parameters
db_params = {
    "host": "34.101.62.255",
    "user": "root",
    "password": "root",
    "database": "ecodo"
}

# Establish the database connection
db_connection = mysql.connector.connect(**db_params)
db_cursor = db_connection.cursor()

# Sample table creation SQL query (you should create this table in your database)
# CREATE TABLE predictions (id INT AUTO_INCREMENT PRIMARY KEY, predicted_class VARCHAR(255));

@app.get("/")
async def hello_world():
    return "Hello, world!"

@app.get("/video")
def get_all_data():
    mydict = methode.video()
    db_cursor.execute("SELECT video FROM sampah")
    result = db_cursor.fetchall()
    mydict.add(result)

    if not result:
        raise HTTPException(status_code=404, detail="Data tidak ditemukan")

    mydict.add(result)
    return {"video": mydict.data}

@app.post("/predict")
async def predict(file: UploadFile = File(...)):
    try:
        model = tf.keras.models.load_model('model_ecodo.h5')
        # Read uploaded image
        content = await file.read()
        image = Image.open(io.BytesIO(content))
        image = image.resize((224, 224))
        image_array = np.array(image)
        input_data = np.expand_dims(image_array, axis=0).astype(np.float32) / 255.0

        # Predict class
        predictions = model.predict(input_data)
        predicted_class_index = np.argmax(predictions).tolist()
        predicted_class = list(class_mapping.keys())[list(class_mapping.values()).index(predicted_class_index)]
        confidence = predictions[0][predicted_class_index]

        # Insert the prediction into the database
        mydict = methode.allin()
        db_cursor.execute("SELECT * FROM sampah WHERE nama = '"+predicted_class+"'")
        result = db_cursor.fetchall()
        mydict.add(result)
        print(predicted_class)
        # Return response
        return {"predicted_class": mydict.data}
    except Exception as e:
        return {"message": f"Error: {e}"}, 400

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("app:app", host="0.0.0.0", port=8080)