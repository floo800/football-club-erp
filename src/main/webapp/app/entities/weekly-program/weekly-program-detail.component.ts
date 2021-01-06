import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWeeklyProgram } from 'app/shared/model/weekly-program.model';

@Component({
  selector: 'jhi-weekly-program-detail',
  templateUrl: './weekly-program-detail.component.html',
})
export class WeeklyProgramDetailComponent implements OnInit {
  weeklyProgram: IWeeklyProgram | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ weeklyProgram }) => (this.weeklyProgram = weeklyProgram));
  }

  previousState(): void {
    window.history.back();
  }
}
