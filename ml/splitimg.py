import os
import shutil


path_dataset = "C:\\Users\\User\\Desktop\\EcoDo\\dataset\\resize"


path_train = "C:/Users/User/Desktop/EcoDo/dataset/fix/train"
path_test = "C:/Users/User/Desktop/EcoDo/dataset/fix/test"


os.makedirs(path_train, exist_ok=True)
os.makedirs(path_test, exist_ok=True)


train_size = int(0.8 * 314)  
test_size = 314 - train_size  


gambar_list = os.listdir(path_dataset)


for gambar in gambar_list[:train_size]:
    src_path = os.path.join(path_dataset, gambar)
    dest_path = os.path.join(path_train, gambar)
    shutil.copy(src_path, dest_path)


for gambar in gambar_list[train_size:]:
    src_path = os.path.join(path_dataset, gambar)
    dest_path = os.path.join(path_test, gambar)
    shutil.copy(src_path, dest_path)

print("Dataset berhasil dibagi menjadi train dan test.")
