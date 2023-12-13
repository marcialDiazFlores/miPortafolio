# -*- coding: cp1252 -*-
#Modulo lista, con las funcionalidades vistas en clase:

import estructura

#lista: valor(any) siguiente(lista)
#any significa cualquier tipo, None significa nada (no tiene valor)
estructura.crear("lista", "valor siguiente")

# Por claridad de conceptos, definimos una lista vacia, la cual para efectos
# practicos, es equivalente a None. Con esto, la lectura del codigo
# ser� mas intuitiva.
listaVacia = None

#ojo: si reemplazamos todos los listaVacia por None, todo deberia funcionar
#de igual manera

#------------------------------------------------------------------------
# esLista: lista -> bool
# True si L es una lista
# ej: esLista(lista(1,listaVacia)) es True

def esLista(L):
    #o es una lista tradicional, o es la lista vacia 
    return type(L) == lista or L == listaVacia

assert esLista(lista(1,listaVacia))

#------------------------------------------------------------------------
# esListaVacia: lista -> bool
# True si L es la lista vacia
# ej esListaVacia(lista(1,listaVacia)) es False
def esListaVacia(L):
    return L == listaVacia
    
assert not esListaVacia(lista(1,listaVacia))
assert esListaVacia(listaVacia)

'''
Ojo: esta funci�n es equivalente a la funcion vacia(lista).
por claridar de lectura de codigo, se recomienda usar la primera.
'''

# vacia: lista -> bool
# True si L es la lista vacia
# ej vacia(lista(1,listaVacia)) es False
def vacia(L):
    return L == listaVacia
    
assert not vacia(lista(1,listaVacia))
assert vacia(listaVacia)


#------------------------------------------------------------------------
# crearLista: any lista -> lista
# dados un elemento cualquiera y una lista, entrega una lista con
# este elemento agregado al principio de la lista.
# ej: crearLista("a",lista("b",listaVacia)) entrega lista("a",lista("b",listaVacia))
def crearLista(elemento,L):
    assert esLista(L)
    return lista(elemento,L)

assert crearLista("a",lista("b",listaVacia)) == lista("a",lista("b",listaVacia))
assert crearLista("c",listaVacia) == lista("c",listaVacia)


#------------------------------------------------------------------------
# cabeza: lista -> any
# entrega el primer valor de una lista
# ej: cabeza(lista("a",lista("b",listaVacia))) entrega "a"

def cabeza(L):
    assert esLista(L)
    
    if esListaVacia(L):
        return listaVacia
    
    return L.valor

#test
assert cabeza(lista("a",lista("b",listaVacia))) == "a"
assert cabeza(listaVacia) == listaVacia

#------------------------------------------------------------------------
# cola: lista -> lista
# entrega la lista, pero sin la primera componente
# ej: cola(lista("a",lista("b",listaVacia))) entrega lista("b",listaVacia)
def cola(L):
    assert esLista(L)
    
    if esListaVacia(L):
        return listaVacia
    
    return L.siguiente

#test
assert cola(lista("a",lista("b",listaVacia))) == lista("b",listaVacia)
assert cola(listaVacia) == listaVacia

#------------------------------------------------------------------------
# largo: lista -> int
# entrega cuantos elementos tiene una lista
# ej: largo(lista(5,lista(4,listaVacia))) entrega 2

def largo(L):
    assert esLista(L)

    #si la lista esta vacia, terminamos
    if esListaVacia(L):
        return 0
    #si no, continuamos contando en el resto de la lista.
    return 1 + largo(cola(L))

#test
assert largo(lista(5,lista(4,listaVacia))) == 2
assert largo(listaVacia) == 0


'''
Estas son las funciones principales
Mas adelante iremos agregando mas funcionalidades utiles para manejar
listas.
'''

