import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITraining, Training } from 'app/shared/model/training.model';
import { TrainingService } from './training.service';
import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from 'app/entities/team/team.service';
import { IWeeklyProgram } from 'app/shared/model/weekly-program.model';
import { WeeklyProgramService } from 'app/entities/weekly-program/weekly-program.service';

type SelectableEntity = ITeam | IWeeklyProgram;

@Component({
  selector: 'jhi-training-update',
  templateUrl: './training-update.component.html',
})
export class TrainingUpdateComponent implements OnInit {
  isSaving = false;
  teams: ITeam[] = [];
  weeklyprograms: IWeeklyProgram[] = [];
  startDateDp: any;
  endDateDp: any;

  editForm = this.fb.group({
    id: [],
    startDate: [],
    endDate: [],
    name: [],
    teamId: [],
    weeklyProgramId: [],
  });

  constructor(
    protected trainingService: TrainingService,
    protected teamService: TeamService,
    protected weeklyProgramService: WeeklyProgramService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ training }) => {
      this.updateForm(training);

      this.teamService.query().subscribe((res: HttpResponse<ITeam[]>) => (this.teams = res.body || []));

      this.weeklyProgramService.query().subscribe((res: HttpResponse<IWeeklyProgram[]>) => (this.weeklyprograms = res.body || []));
    });
  }

  updateForm(training: ITraining): void {
    this.editForm.patchValue({
      id: training.id,
      startDate: training.startDate,
      endDate: training.endDate,
      name: training.name,
      teamId: training.teamId,
      weeklyProgramId: training.weeklyProgramId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const training = this.createFromForm();
    if (training.id !== undefined) {
      this.subscribeToSaveResponse(this.trainingService.update(training));
    } else {
      this.subscribeToSaveResponse(this.trainingService.create(training));
    }
  }

  private createFromForm(): ITraining {
    return {
      ...new Training(),
      id: this.editForm.get(['id'])!.value,
      startDate: this.editForm.get(['startDate'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
      name: this.editForm.get(['name'])!.value,
      teamId: this.editForm.get(['teamId'])!.value,
      weeklyProgramId: this.editForm.get(['weeklyProgramId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITraining>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
