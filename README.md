# challengeMediosDePago
Challenge Técnico de medios de pago



El proyecto tiene tanto un ejecutable como tambi�n una APIREST deployada en heroku, la cual esta ser�a su URL con la cual seria la base para poder empezar a realizar comunicaciones y pedidos con la API:

* https://medio-de-pago-2.herokuapp.com



# EJECUTABLE

El ejecutable cuenta con un panel de comandos con el cual el usuario puede interactuar con ella para solicitar distintos tipos de informaci�n.

La misma fue provisto en base a una base de conocimiento de 5 operaciones y 3 tarjetas creadas para dar soporte y realizar comprobaciones de las mismas.

La informaci�n de las distintas operaciones y tarjetas se mostrar� en pantalla a partir de un TOSTRING que tiene cada clase la cual le revelar� toda la informaci�n disponible de la misma. 


# APIREST deployada

La APIREST deployada cuenta con los siguientes ENDPOINTS para poder realizar la comunicaci�n:

* /operacion/{id_operacion}
GET

A partir del ID_OPERACION buscar� en la base de conocimiento la Operaci�n asociada y retorna �nicamente esa opearci�n

EJEMPLO DE RESPUESTA DE JSON

  {
    "id": 2,
    "montoPagado": 100.0,
    "tarjeta": {
      "nroTarjeta": 564,
      "datosPersona": {
        "nombre": "Florencia",
        "apellido": "Williams"
      },
      "marcaTarjeta": {
        "nombre": "AMEX",
        "tasaInteres": 1.0
      },
      "fechaVencimiento": {
        "mes": "Octubre",
        "anio": 2021
      }
    },
    "fechaOperacion": {
      "year": 2021,
      "month": 6,
      "dayOfMonth": 1,
      "hourOfDay": 23,
      "minute": 59,
      "second": 32
    }
  }

RESPUESTAS

***OK
***Excepcion de conversion numerica

	Esta excepci�n se da lugar cuando el Path variable de ID_OPERACION no es un n�mero por lo tanto no se puede realizar la conversi�n a un Integer. En caso de ocurrencia, deber� responder con un BAD_REQUEST

***EXCEPCION
	
	Excepci�n que puede ser causada por diversos facotes durante la ejecuci�n del c�digo. Devuelve un HTTP NOT_FOUND


* /operacion/tasaInteres/{id_operacion}
GET

La informaci�n que retorna este controlador es la informaci�n a partir de una operacion que est� en la base de conocimiento, se obtiene la TASA DE INTERES (el monto al cual se le debe cobrar al cliente por realizar la operaci�n) como tambi�n la informaci�n de la tarjeta.

Tipos de respuestas

***OK
***Excepcion de conversion numerica

	Esta excepci�n se da lugar cuando el Path variable de ID_OPERACION no es un n�mero por lo tanto no se puede realizar la conversi�n a un Integer. En caso de ocurrencia, deber� responder con un BAD_REQUEST

***EXCEPCION
	
	Excepci�n que puede ser causada por diversos facotes durante la ejecuci�n del c�digo. Devuelve un HTTP NOT_FOUND



* /operacion
GET

Devuelve todas las operaciones que est�n registradas en al base de conocimientos a modo de consulta. La misma se muestra en un JSON de un array que muestra cada una de dichas operaciones.

Ejemplo de respuesta en JSON
[
  {
    "id": 2,
    "montoPagado": 100.0,
    "tarjeta": {
      "nroTarjeta": 564,
      "datosPersona": {
        "nombre": "Florencia",
        "apellido": "Williams"
      },
      "marcaTarjeta": {
        "nombre": "AMEX",
        "tasaInteres": 1.0
      },
      "fechaVencimiento": {
        "mes": "Octubre",
        "anio": 2021
      }
    },
    "fechaOperacion": {
      "year": 2021,
      "month": 6,
      "dayOfMonth": 1,
      "hourOfDay": 23,
      "minute": 59,
      "second": 32
    }
  },
  {
    "id": 4,
    "montoPagado": 500.0,
    "tarjeta": {
      "nroTarjeta": 8735,
      "datosPersona": {
        "nombre": "Ricardo",
        "apellido": "Henrique"
      },
      "marcaTarjeta": {
        "nombre": "NARA",
        "tasaInteres": 1.0
      },
      "fechaVencimiento": {
        "mes": "Abril",
        "anio": 2021
      }
    },
    "fechaOperacion": {
      "year": 2021,
      "month": 6,
      "dayOfMonth": 1,
      "hourOfDay": 23,
      "minute": 59,
      "second": 32
    }
  }
]

Tipos de respuestas

***OK
***EXCEPCION
	
	Excepci�n que puede ser causada por diversos facotes durante la ejecuci�n del c�digo. Devuelve un HTTP NOT_FOUND




* /operacion
POST

Recibiendo un JSON en el REQUESTBODY, este endpoint se encarga de registrar una nueva operaci�n en la base de conocimiento. Puede retornar 3 tipos de respuestas

EJEMPPLO DE ENTRADA DE JSON
{
  "id": 1322,
  "montoPagado": 1300.0,
  "tarjeta": {
    "nroTarjeta": 123,
    "datosPersona": {
      "nombre": "Ricardo",
      "apellido": "Henrique"
    },
    "marcaTarjeta": {
      "nombre": "VISA",
      "tasaInteres": 1.0
    },
    "fechaVencimiento": {
      "mes": "Diciembre",
      "anio": 2021
    }
  },
  "fechaOperacion": {
    "year": 2021,
    "month": 6,
    "dayOfMonth": 1,
    "hourOfDay": 22,
    "minute": 7,
    "second": 34
  }
}

REPUESTAS
	***  OK
	***  Excepcion Numero INvalidos para operacion
			Esta caso informa que los n�meros utilizados para realizar la operaci�n no pueden ser almacenados por lo tanto se rechaza la operaci�n, estos son para los casos de MONTO<0 y MONTO>1000 RESPECTIVAMENTE. La respuesta del endpoint es la de un HTTP STATUTS NOT_ACCEPTABLE.

	***  EXCEPCION
			Excepci�n que puede ser causada por diversos facotes durante la ejecuci�n del c�digo. Devuelve un HTTP NOT_ACCEPTABLE


* /tarjeta
POST

Al igual que la anterio, su funci�n es brindar un dada de alta a una tarjeta para que sea registrada en la base de conocimientos de la aplicaci�n.

EJEMPLO DE RESPUESTA DE JSON

{
  "nombre": "AMEX",
  "tasaInteres": 1.0
}

RESPUESTA
Cuenta con 3 tipos de respuestas

	*** OK
	*** Excepcion Tarjeta Vencida
			En caso de que se tratase de una tarjeta cuya fecha de vencimiento sea menor que la fecha actual entonces no se guardar� dicha tarjeta en la base de conocimiento y retorna un NOT_ACCEPTABLE
	***  Excepcion
			Excepci�n que puede ser causada por diversos facotes durante la ejecuci�n del c�digo. Devuelve un HTTP NOT_ACCEPTABLE


*/operacion/marcaTarjeta/{id_operacion}
GET

A parrtir de un Numero de tarjeta, esta busca en la base de conocimiento y devuelve la marca de la tarjeta

EJEMPLO de respuesta en JSON:
{
    "nroTarjeta": 123,
    "datosPersona": {
      "nombre": "Ricardo",
      "apellido": "Henrique"
    },
    "marcaTarjeta": {
      "nombre": "VISA",
      "tasaInteres": 1.0
    },
    "fechaVencimiento": {
      "mes": "Diciembre",
      "anio": 2021
    }
  }
Respuestas

***OK
***Excepcion de conversion numerica

	Esta excepci�n se da lugar cuando el Path variable de ID_OPERACION no es un n�mero por lo tanto no se puede realizar la conversi�n a un Integer. En caso de ocurrencia, deber� responder con un BAD_REQUEST

***EXCEPCION
	
	Excepci�n que puede ser causada por diversos facotes durante la ejecuci�n del c�digo. Devuelve un HTTP NOT_FOUND

