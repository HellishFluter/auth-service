# Environment variables

| Name                     | Required | Default                                 | Description                                  | Example                                      |
|--------------------------|----------|-----------------------------------------|----------------------------------------------|----------------------------------------------|
| GRPC_PORT                | No       | 9090                                    | GRPC port                                    | 9090                                         |
| GRPC_ADDRESS             | No       | *                                       | GRPC address                                 | unix:/run/grpc-server                        |
| POSTGRESQL_JDBC_DRIVER   | No       | org.postgresql.Driver                   | Postgres DB driver                           | org.postgresql.Driver                        |
| POSTGRESQL_JDBC_URL      | Yes      |                                         | Postgres DB url                              | jdbc:postgresql://postgres:5432/auth_service |
| POSTGRESQL_JDBC_USERNAME | Yes      |                                         | Postgres DB user                             | admin                                        |
| POSTGRESQL_JDBC_PASSWORD | Yes      |                                         | Postgres DB user password                    | admin                                        |
| HIBERNATE_DDL_AUTO       | No       | validate                                | Hibernate schema generation strategies       | validate                                     |
| HIBERNATE_DIALECT        | No       | org.hibernate.dialect.PostgreSQLDialect | Bridge between Java JDBC types and SQL types | org.hibernate.dialect.PostgreSQLDialect      |
| PUBLIC_KEY               | Yes      |                                         | Public Key RS256 for token verifying (2048b) | MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh7Rlwi6t56PzsHghhpUsPfjUQ/POYXoFBwxCIa5pwIzD+33SDU/yQDqThVXg5cyDC7bDl1i+Nqk1LCFW7i/83zpCYt8m4oadhOMuJKBT8JUmk8a34dd3DDPm/SxoRJVVgcWc6Y0UHS9j3T00qEOZFzGsaU5T/GkOAYcnZ8Dz0SRXFT6pffx7i3ZEcmyREVCBSt++p173FC3K0BXne14c3BiKx9cv2tco+AVnMEkioaCeuzkmTU3a3HLHYv9/ZZOn9c5HG4eBfpQaCQDLnxQleogafdc3on9sBCbXXu7vU1M0UvMjlLo4M6Zm/Fyid6KkOrFOwdrx05GLkCZps7c7BwIDAQAB|
