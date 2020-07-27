# Java 8
# ARG java_image_tag=8-jre-slim

# Java 11
ARG java_image_tag=11.0.8-jre-slim
ARG spark_uid=185

FROM openjdk:${java_image_tag}

RUN set -ex && \
    sed -i 's/http:\/\/deb.\(.*\)/https:\/\/deb.\1/g' /etc/apt/sources.list && \
    apt-get update && \
    ln -s /lib /lib64 && \
    apt install -y bash tini libc6 libpam-modules krb5-user libnss3 procps && \
    mkdir -p /opt/spark && \
    mkdir -p /opt/spark/examples && \
    mkdir -p /opt/spark/work-dir && \
    touch /opt/spark/RELEASE && \
    rm /bin/sh && \
    ln -sv /bin/bash /bin/sh && \
    echo "auth required pam_wheel.so use_uid" >> /etc/pam.d/su && \
    chgrp root /etc/passwd && chmod ug+rw /etc/passwd && \
    rm -rf /var/cache/apt/*


COPY spark/dist/jars /opt/spark/jars
COPY spark/dist/bin /opt/spark/bin
COPY spark/dist/sbin /opt/spark/sbin
COPY spark/dist/kubernetes/dockerfiles/spark/entrypoint.sh /opt/
COPY spark/dist/kubernetes/dockerfiles/spark/decom.sh /opt/
COPY spark/dist/examples /opt/spark/examples
# ------------------
# insert my custom jars
COPY custom_jars/ /opt/spark/examples/
# ------------------
COPY spark/dist/kubernetes/tests /opt/spark/tests
COPY spark/dist/data /opt/spark/data

ENV SPARK_HOME /opt/spark

WORKDIR /opt/spark/work-dir
RUN chmod g+w /opt/spark/work-dir
RUN chmod a+x /opt/decom.sh

ENTRYPOINT [ "/opt/entrypoint.sh" ]

# Specify the User that the actual main process will run as
USER ${spark_uid}
