import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWeeklyProgram } from 'app/shared/model/weekly-program.model';
import { WeeklyProgramService } from './weekly-program.service';
import { WeeklyProgramDeleteDialogComponent } from './weekly-program-delete-dialog.component';

@Component({
  selector: 'jhi-weekly-program',
  templateUrl: './weekly-program.component.html',
})
export class WeeklyProgramComponent implements OnInit, OnDestroy {
  weeklyPrograms?: IWeeklyProgram[];
  eventSubscriber?: Subscription;

  constructor(
    protected weeklyProgramService: WeeklyProgramService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.weeklyProgramService.query().subscribe((res: HttpResponse<IWeeklyProgram[]>) => (this.weeklyPrograms = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWeeklyPrograms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWeeklyProgram): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWeeklyPrograms(): void {
    this.eventSubscriber = this.eventManager.subscribe('weeklyProgramListModification', () => this.loadAll());
  }

  delete(weeklyProgram: IWeeklyProgram): void {
    const modalRef = this.modalService.open(WeeklyProgramDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.weeklyProgram = weeklyProgram;
  }
}
