import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventsService} from "../events.service";
import {Reservation} from "../reservation";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface ReservationDataDialog {
  reservedTickets: number;
}

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})

export class EventDetailsComponent implements OnInit {
  displayedColumns: string[] = ['id', 'reservedTickets'];
  reservations: Reservation[];
  eventId: string;
  reservedTickets: number;

  constructor(
    private route: ActivatedRoute,
    private eventsService: EventsService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.eventId = this.route.snapshot.paramMap.get('id');
    this.loadReservations();
  }

  loadReservations() {
    this.eventsService.getReservationsForEvent(this.eventId).subscribe(reservations => this.reservations = reservations);
  }

  openReservationDialog(): void {
    const dialogRef = this.dialog.open(ReservationDialog, {
      width: '250px',
      data: { reservedTickets: this.reservedTickets }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.reservedTickets = result;

      this.placeReservation();
    });
  }

  placeReservation(): void {
    this.eventsService.makeReservationForEvent(this.eventId, this.reservedTickets)
      .subscribe(() => this.loadReservations());
  }
}

@Component({
  selector: 'reservation-dialog',
  templateUrl: 'reservation-dialog.html',
})
export class ReservationDialog {

  constructor(
    public dialogRef: MatDialogRef<ReservationDialog>,
    @Inject(MAT_DIALOG_DATA) public data: ReservationDataDialog) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
