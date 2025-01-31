#\!/usr/bin/bash
awk -F";" '$4 == "TX" {print $8}' Electric_Vehicle_Population_Data.csv
