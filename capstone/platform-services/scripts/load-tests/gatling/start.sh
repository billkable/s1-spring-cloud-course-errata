#!/bin/bash

REPORTS_DIR=`pwd`/reports
SIMULATION_DIR=`pwd`/simulations

gatling -sf $SIMULATION_DIR -rf $REPORTS_DIR -rd CreateTimesheets