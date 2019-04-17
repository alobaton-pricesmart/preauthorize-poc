# preauthorize-poc

Custom PreAuthorize flow with Spring Security.

## Overview

The Spring Security annotation @PreAuthorize allow us to have custom security expression. This POC show an easy way to do this, having access to the Request Context and the Security Context using a custom Method Expression (also you can use a custom ExpressionEvaluator) and an annotation to wrap all of this.

## How to start?

In order to start the POC use:

```bash
$ git clone https://github.com/alobaton/preauthorize-poc.git

$ cd preauthorize-poc
$ mvn clean install

$ mvn spring-boot:run
```

## Contributors

[<img alt="alobaton" src="https://avatars1.githubusercontent.com/u/9356067?s=460&v=4" width="117">](https://github.com/alobaton)

## License

[MIT](https://github.com/alobaton/preauthorize-poc/blob/master/LICENSE)
