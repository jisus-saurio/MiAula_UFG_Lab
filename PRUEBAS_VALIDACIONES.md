\# Pruebas de validación - Laboratorio 3



Responsable: Daniel

Fecha: 24 de octubre de 2026

Rama: daniel



\## Login

| # | Prueba | Resultado esperado | Resultado |

|---|--------|--------------------|-----------|

| L1 | Campos vacíos | Mensaje "Ingrese correo y contraseña." | OK |

| L2 | Clave incorrecta | Mensaje de error, no avanza | OK |

| L3 | Correo inexistente | Mensaje de error, no avanza | OK |

| L4 | Credenciales correctas | Abre el Home | OK |



\## Home

| # | Prueba | Resultado esperado | Resultado |

|---|--------|--------------------|-----------|

| H1 | Todo vacío | Error de campo vacío | OK |

| H2 | Nombre vacío | Error de campo vacío | OK |

| H3 | Edificio vacío | Error de campo vacío | OK |

| H4 | Planta sin elegir | Error de campo vacío | OK |

| H5 | Capacidad vacía | Error de campo vacío | OK |

| H6 | Tipo de aula sin elegir | Error de campo vacío | OK |

| H7 | Responsable vacío | Error de campo vacío | OK |

| H8 | Estado sin elegir | Error de campo vacío | OK |

| H9 | Capacidad 0 | Error: debe ser mayor que 0 | OK |

| H10 | Capacidad -5 | Error: debe ser mayor que 0 | OK |

| H11 | Capacidad "abc" | Error: debe ser entero | OK |

| H12 | Capacidad 3.5 | Error: debe ser entero | OK |

| H13 | Nombre solo espacios | Error de campo vacío | OK |

| H14 | Datos válidos | Guarda el aula | OK |

| H15 | Aula duplicada (mismo nombre y edificio) | Error de duplicado | OK |

| H16 | Mismo nombre, otro edificio | Guarda bien | OK |

| H17 | Botón Limpiar | Vacía el formulario | OK |

| H18 | Cerrar sesión | Vuelve al Login | OK |



\## Observaciones

\- La planta se elige desde un ComboBox (valores 1 a 10), por lo que no admite 0 ni negativos.

\- Las pruebas fallidas no guardaron registros en la base de datos (ver evidencias/BD\_aulas.png).

\- Capturas de cada prueba en la carpeta `evidencias/`.

