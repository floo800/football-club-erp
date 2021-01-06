import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWeeklyProgram, WeeklyProgram } from 'app/shared/model/weekly-program.model';
import { WeeklyProgramService } from './weekly-program.service';

@Component({
  selector: 'jhi-weekly-program-update',
  templateUrl: './weekly-program-update.component.html',
})
export class WeeklyProgramUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    week: [],
  });

  constructor(protected weeklyProgramService: WeeklyProgramService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ weeklyProgram }) => {
      this.updateForm(weeklyProgram);
    });
  }

  updateForm(weeklyProgram: IWeeklyProgram): void {
    this.editForm.patchValue({
      id: weeklyProgram.id,
      week: weeklyProgram.week,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const weeklyProgram = this.createFromForm();
    if (weeklyProgram.id !== undefined) {
      this.subscribeToSaveResponse(this.weeklyProgramService.update(weeklyProgram));
    } else {
      this.subscribeToSaveResponse(this.weeklyProgramService.create(weeklyProgram));
    }
  }

  private createFromForm(): IWeeklyProgram {
    return {
      ...new WeeklyProgram(),
      id: this.editForm.get(['id'])!.value,
      week: this.editForm.get(['week'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWeeklyProgram>>): void {
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
}
