import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {ReservationEvent} from "./reservationevent";

@Injectable({
  providedIn: 'root'
})
export class EventsService {
  constructor(private httpClient: HttpClient) {
  }

  getEvents(): Observable<ReservationEvent[]> {
    return this.httpClient.get<ReservationEvent[]>('http://localhost:8080/events/available');
  }
}
