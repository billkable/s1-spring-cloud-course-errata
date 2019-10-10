#!/bin/bash

## Run newman in same directory as collection and environment files

newman run --folder "Primary Flow" \
    --environment pal-tracker-flow.postman_environment.json \
    pal-tracker-flow.postman_collection.json