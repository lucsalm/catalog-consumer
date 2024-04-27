# Catalog Consumer

Este documento está disponível em [Inglês](https://github.com/lucsalm/catalog-consumer/blob/main/README.md), 
mas também está disponível em [Português](https://github.com/lucsalm/catalog-consumer/blob/main/README-pt-BR.md).

## Visão Geral
Este projeto é uma implementação do [Desafio Backend AnotaAi](https://github.com/githubanotaai/new-test-backend-nodejs). Basicamente, o desafio envolve a criação de uma 
[API](https://github.com/lucsalm/catalog-api) capaz de realizar inserções e atualizações no catálogo de produtos de um proprietário no MongoDB. 
Ele envia quaisquer alterações para um tópico de notificação no AWS SNS, que está conectado a uma fila AWS SQS
ouvida por outra [aplicação](https://github.com/lucsalm/catalog-consumer)  responsável por criar uma representação JSON dos dados do MongoDB.
Essa aplicação então insere e atualiza um arquivo em um bucket do AWS S3 para consultas rápidas.

## Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D.svg?style=for-the-badge&logo=Swagger&logoColor=black)
![Mongodb](https://img.shields.io/badge/MongoDB-47A248.svg?style=for-the-badge&logo=MongoDB&logoColor=white)
![AWS](https://img.shields.io/badge/Amazon%20AWS-232F3E.svg?style=for-the-badge&logo=Amazon-AWS&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white)

## Arquitetura

![Arquitetura](https://raw.githubusercontent.com/lucsalm/catalog-consumer/main/img/arquitetura.png)

## Como Usar

1. Configure os serviços em sua conta da AWS:
    1. Crie um tópico no SNS.
    2. Crie uma fila no SQS.
        - Inscreva a fila no tópico já criado.
        - Atribua a seguinte permissão:
           ```json 
            {
             "Sid": "{SID}",
             "Effect": "Allow",
             "Principal": "*",
             "Action": "sqs:SendMessage",
             "Resource": "arn:aws:sqs:{REGION}:{ACCOUNT_ID}:{QUEUE_NAME}",
             "Condition": {
               "ArnEquals": {
                 "aws:SourceArn": "arn:aws:sns:{REGION}:{ACCOUNT_ID}:{TOPIC_NAME}"
               }
             }
            }
            ```
    3. Crie um bucket no S3.
        - Habilite o acesso público.
        - Atribua a seguinte permissão:
            ```json
            {
             "Sid": "{SID}",
             "Effect": "Allow",
             "Principal": "*",
             "Action": "s3:GetObject",
             "Resource": "arn:aws:s3:::{BUCKET_NAME}/*.json"
            }
            ```
    4. Crie um usuário no IAM:
        - Atribua as seguintes políticas:
        ```yaml
        AmazonS3FullAccess
        AmazonSQSFullAccess
        AWSIoTDeviceDefenderPublishFindingsToSNSMitigationAction
       ```
        - Crie credenciais de chave de acesso.

2. Verifique se o Docker está instalado em sua máquina.
3. Clone este repositório em seu ambiente local.
4. Configure suas variáveis de ambiente da AWS no arquivo `docker-compose.yaml`:
   ```yaml
     AWS_REGION
     AWS_SNS_TOPIC_ARN
     AWS_S3_BUCKET_NAME
     AWS_SQS_NAME
     AWS_CREDENTIALS_KEY_ACCESS
     AWS_CREDENTIALS_KEY_SECRET
      ```
5. No terminal, execute o seguinte comando para construir e iniciar o contêiner Docker:
    - No Linux, execute:
        ```bash
        docker compose up
        ```

    - No Windows, execute:
        ```bash
        docker-compose up
        ```

6. Após os contêineres serem construídos e a aplicação ser iniciada,
   acesse [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) para visualizar sua documentação. Você deverá ver a seguinte tela:
   ![Swagger-api](https://raw.githubusercontent.com/lucsalm/catalog-consumer/main/img/swagger.png)

**Observações:**

- Certifique-se de que as portas `8080` e `8081` não estão sendo usadas por outra aplicação em seu sistema para evitar
  conflitos. Se necessário, você pode modificar o mapeamento de porta no arquivo `docker-compose.yml`.
