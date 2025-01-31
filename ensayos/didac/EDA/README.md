+ cabecera head 5 <file>
+ cola tail 7 <file>
+ contar columnas

    substituimos los separadores por un retorno de linea. Contamos las lineas.
    ```bash
    head -1 flags.csv | sed -e s/:/\\n/g | wc -l
    ```

+ extraer datos de una columna


    ```bash
    awk -F: '{print $10}' flags.csv | head -5
    ```


+ extraer un subconjunto de columnas


    ```bash
    cut -d: -f1,10,11 flags.csv
    ```


+ sumar los valores de una columna, la ocho

    ```bash
    awk -F ',' ' {sum += $8} END {print sum}' detalles_banderas.csv 
    ```

+ cual es el máximo valor de los valores de una columna

    ```bash
    sort -k8 -n  detalles_banderas.csv | head -2
    ```

+ cuantos paises hay en Europa
    ```bash
    sort -k8 -n  detalles_banderas.csv | head -2
    ```

+ De cuantos continente hay banderas en el ficheros

    ```bash
    cut -d: -f11 flags.csv | sort | uniq | grep -v Continent | wc -l
    cut -d: -f11 flags.csv | sort -u | grep -v Continent | wc -l
    ```
+ Hay paises repetidos?
    Analizo los valores únicos y los comparo con el total de líneas del fichero
    ```bash
    cut -d: -f10 flags.csv | sort -u | grep -v Name | wc -l
    ```

    + Encontrar las lineas que están vacías. 
        Usamos las capacidades Regex de grep. Bucamos la cadena vacía con el patron '^$'.
        En este caso la columna 17 tiene 187 lineas vacias. queremos contarlas

    ```bash
    cut -d: -f17 flags.csv | sort | grep -v Text | grep '^$'| wc -l
    ```    

     

    + para saber valor único que aparece **más** veces en la columna 8
    ```bash
     grep -i Nissan Electric_Vehicle_Population_Data.csv | cut -d';' -f8 | sort | uniq -c | sort -t' ' -k1 -r | head -1
    ```   

     + para saber valor único que aparece **menos** veces en la columna 8
    ```bash
     grep -i Nissan Electric_Vehicle_Population_Data.csv | cut -d';' -f8 | sort | uniq -c | sort -t' ' -k1  | head -1
    ```  

  + Para saber el **valor medio** de un valor en la columna 11 por las categorías que tenemos en la columna 7

    ```bash
    awk -F";" '{count[$7]++; suma[$7]=suma[$7] + $11;} END {for (make in count) print make, suma[make]/count[make]}' datos.csv
    ``` 

    ## no repitas consultas. Haz un script!!!!

    puedes crear un sript bash para no repetir preguntas, como la de averiguar en que coluna hay una informacion.

    + Averigua dónde está bash en tu máquina 
    ```bash
    $ which bash
    /usr/bin/bash
    ```
    usa esa ruta como la  de un guion o script de bash. yo lo llamo **columnas.sh**
    $ echo "#\!/usr/bin/bash" > columnas.sh
    $ echo "head -1 flags.csv | sed -e 's/:/:\n/g'| grep -n ':'" >> columnas.sh
    $ chmod +x columnas.sh
    $ ./columnas.sh

    Notad el diferente uso del primer **>** y del segundogit log --oneline **>>**.
    chmod +x le da permisos de ejecuición al guion.

    # Sin miedo a mayúsculas y minusculas en el grep: usa el flag -i
