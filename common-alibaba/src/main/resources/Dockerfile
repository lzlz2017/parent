FROM openjdk
ARG APP_NAME
ENV APP_NAME $APP_NAME
ENV LANG zh_CN.UTF-8
ADD /$APP_NAME /home/admin
RUN apk add --update --no-cache curl
ENTRYPOINT ["java","-jar","/$APP_NAME"]