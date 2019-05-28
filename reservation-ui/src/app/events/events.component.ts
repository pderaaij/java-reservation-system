import { Component, OnInit } from '@angular/core';
import { EventsService } from "../events.service";
import {ReservationEvent} from "../reservationevent";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  displayedColumns: string[] = ['title', 'date', 'capacity'];
  events: ReservationEvent[];

  constructor(private eventService: EventsService) { }

  ngOnInit() {
    this.loadEvents();
  }

  loadEvents() {
    this.eventService.getEvents().subscribe(events => this.events = events);
  }

}
