import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWeeklyProgram } from 'app/shared/model/weekly-program.model';
import { WeeklyProgramService } from './weekly-program.service';

@Component({
  templateUrl: './weekly-program-delete-dialog.component.html',
})
export class WeeklyProgramDeleteDialogComponent {
  weeklyProgram?: IWeeklyProgram;

  constructor(
    protected weeklyProgramService: WeeklyProgramService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.weeklyProgramService.delete(id).subscribe(() => {
      this.eventManager.broadcast('weeklyProgramListModification');
      this.activeModal.close();
    });
  }
}
