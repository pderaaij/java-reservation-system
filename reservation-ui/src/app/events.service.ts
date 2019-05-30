import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {ReservationEvent} from "./reservationevent";
import {Reservation} from "./reservation";

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
}
