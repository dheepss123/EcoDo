import os
import shutil
from pathlib import Path


source_path = Path("C:/Users/User/Desktop/EcoDo/dataset/anotate")


train_path = Path("C:/Users/User/Desktop/EcoDo/dataset/fix/train")
test_path = Path("C:/Users/User/Desktop/EcoDo/dataset/fix/test")


xml_files = sorted(source_path.glob("*.xml"))


total_files = len(xml_files)
train_size = int(0.8 * total_files)


train_files = xml_files[:train_size]
test_files = xml_files[train_size:]


for file in train_files:
    shutil.copy(file, train_path)


for file in test_files:
    shutil.copy(file, test_path)

print("Dataset berhasil dibagi menjadi train dan test.")
