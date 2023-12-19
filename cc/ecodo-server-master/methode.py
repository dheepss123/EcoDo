class allin(dict): 
    def __init__(self): 
        self.data = {
            "data": []
        }
           
    def add(self, result): 
        for item in result:
            data_item = {
                "id": item[0],
                "nama": item[1],
                "artikel": item[2],
                "video": item[3],
            }
            self.data["data"]=(data_item)

class video(dict): 
    def __init__(self): 
        self.data = {
            "data": []
        }
           
    def add(self, result): 
        for item in result:
            data_item = {
                #"id": item[0],
                #"nama": item[1],
                #"artikel": item[2],
                "video": item[0],
            }
            self.data["data"].append(data_item)