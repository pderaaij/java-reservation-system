import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventsService} from "../events.service";
import {Reservation} from "../reservation";
import {Observable} from "rxjs";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'reservedTickets'];
  reservations: Reservation[];

  constructor(
    private route: ActivatedRoute,
    private eventsService: EventsService
  ) { }

  ngOnInit() {
    this.loadReservations();
  }

  loadReservations() {
    const id = this.route.snapshot.paramMap.get('id');
    this.eventsService.getReservationsForEvent(id).subscribe(reservations => this.reservations = reservations);
  }

}
