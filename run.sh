#!/bin/bash
APP_NAME=$(mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout)
APP_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
java -Xmx2g -jar target/$APP_NAME-$APP_VERSION.jar