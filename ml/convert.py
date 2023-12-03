import os
import glob
import pandas as pd
import xml.etree.ElementTree as ET

def xml_to_csv(input_path, output_path):
    xml_list = []
    for xml_file in glob.glob(input_path + '/*.xml'):
        tree = ET.parse(xml_file)
        root = tree.getroot()
        for member in root.findall('object'):
            value = (root.find('filename').text,
                     int(root.find('size')[0].text),
                     int(root.find('size')[1].text),
                     member[0].text,
                     int(member[4][0].text),
                     int(member[4][1].text),
                     int(member[4][2].text),
                     int(member[4][3].text)
                     )
            xml_list.append(value)
    column_name = ['filename', 'width', 'height', 'class', 'xmin', 'ymin', 'xmax', 'ymax']
    xml_df = pd.DataFrame(xml_list, columns=column_name)

    # Membuat direktori output jika belum ada
    os.makedirs(output_path, exist_ok=True)

    xml_df.to_csv(os.path.join(output_path, 'label.csv'), index=None)
    print(f'Successfully converted xml to csv. Saved at {output_path}')

def main():
    input_path = r'C:\EcoDo\dataset\anotate'
    output_path = r'C:\EcoDo\dataset\resize'
    xml_to_csv(input_path, output_path)

if __name__ == "__main__":
    main()
