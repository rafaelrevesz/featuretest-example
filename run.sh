#!/bin/bash

# ./run.sh -e int -l debug -a "--tags @Example"

manual() {
    cat <<EOF
Bash wrapper for feature tests.

-e name  Target environment name=dev|int (default is int)
-l level Log level=trace|debug|info|warn|error (default is info)
-a args  Extra arguments for the cucumber CLI
-c N     Run N times sequentially (default is 1)
-h       Print this help message.
EOF
}

environment="default"
logLevel="info"
args="-p pretty "
runCount="1"

while getopts ":e:a:c:l:h:" opt; do
    case "${opt}" in
        e) environment="${OPTARG}"; ;;
        a) args+="${OPTARG}"; ;;
        c) runCount="${OPTARG}"; ;;
        l) logLevel="${OPTARG}"; ;;
        h) manual; exit 0; ;;
        :) manual; exit 1; ;;
        \?) manual; exit 1
    esac
done

if [[ ! "${runCount}" =~ ^[1-9]+[0-9]*$ ]]; then
    echo "Invalid run count '${runCount}'!" >&2
    manual
    exit 1
fi

set -e

./gradlew bootJar

echo "Run count: ${runCount}"
for i in $(seq "${runCount}"); do
    echo "Run number: $i"
    java -jar \
-Dspring.profiles.active="${environment}" \
-Dlogging.level.com.example="${logLevel}" \
build/libs/featuretest.jar ${args}
done
