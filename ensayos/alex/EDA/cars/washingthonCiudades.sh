#!/bin/bash
awk -F";" '$4 == "WA" {print $3}' Electric_Vehicle_Population_Data.csv | uniq | wc -l
