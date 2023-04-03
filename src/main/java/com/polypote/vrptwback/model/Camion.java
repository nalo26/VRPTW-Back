package com.polypote.vrptwback.model;


import java.util.LinkedList;


public record Camion(LinkedList<Client> clients, int distance) {

}
