#\!/usr/bin/bash
head -n 1 Electric_Vehicle_Population_Data.csv | sed 's/;/\n/g' | grep -n "Model Year"
