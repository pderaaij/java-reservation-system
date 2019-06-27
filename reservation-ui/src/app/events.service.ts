import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable, of} from "rxjs";
import {ReservationEvent} from "./reservationevent";
import {Reservation} from "./reservation";
import {catchError} from "rxjs/operators";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EventsService {
  constructor(private httpClient: HttpClient) {
  }

  getEvents(): Observable<ReservationEvent[]> {
    return this.httpClient.get<ReservationEvent[]>('http://localhost:8080/events/available');
  }

  getReservationsForEvent(id: string): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>('http://localhost:8080/events/' + id + '/reservations');
  }

  // TODO: improve: error handling
  makeReservationForEvent(eventId: string, reservedTickets: number) {
    let body = {
      eventId: eventId + "-1",
      requestedTickets: reservedTickets
    };
    return this.httpClient
      .post('http://localhost:8080/events/' + eventId + '/reservations', body, httpOptions)
      .pipe(
        catchError(val => of(console.error(val)))
      );
  }
}
