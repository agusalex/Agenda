Maven goals: clean package install exec:java
//FIXME :
 - edicion de etiquetas y localidades, cambiar las que esten en uso  de personas al editarlas( correccion de excepctiones de tipo SQL )
 - Evitar agregado repetido de etiquetas/localidades... es necesario?
 por ej, San Miguel, Buenos Aires y San Miguel, Tucuman las dos localidades se llaman igual pero tienen diferentes provincias pero no son la misma localidad
 - Chequear que los campos no superen limite impuesto por db en el script
  -Si la fecha es igual a la de hoy (no la cambio) entonces se guarda como null






