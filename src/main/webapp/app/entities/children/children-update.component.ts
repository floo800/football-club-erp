import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IChildren, Children } from 'app/shared/model/children.model';
import { ChildrenService } from './children.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from 'app/entities/team/team.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

type SelectableEntity = ITeam | IUser;

@Component({
  selector: 'jhi-children-update',
  templateUrl: './children-update.component.html',
})
export class ChildrenUpdateComponent implements OnInit {
  isSaving = false;
  teams: ITeam[] = [];
  users: IUser[] = [];
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    name: [],
    firstName: [],
    birthDate: [],
    birthCountry: [],
    birthCity: [],
    photo: [],
    photoContentType: [],
    teamId: [],
    parentId: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected childrenService: ChildrenService,
    protected teamService: TeamService,
    protected userService: UserService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ children }) => {
      this.updateForm(children);

      this.teamService.query().subscribe((res: HttpResponse<ITeam[]>) => (this.teams = res.body || []));

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(children: IChildren): void {
    this.editForm.patchValue({
      id: children.id,
      name: children.name,
      firstName: children.firstName,
      birthDate: children.birthDate,
      birthCountry: children.birthCountry,
      birthCity: children.birthCity,
      photo: children.photo,
      photoContentType: children.photoContentType,
      teamId: children.teamId,
      parentId: children.parentId,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('footballClubErpApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const children = this.createFromForm();
    if (children.id !== undefined) {
      this.subscribeToSaveResponse(this.childrenService.update(children));
    } else {
      this.subscribeToSaveResponse(this.childrenService.create(children));
    }
  }

  private createFromForm(): IChildren {
    return {
      ...new Children(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      birthDate: this.editForm.get(['birthDate'])!.value,
      birthCountry: this.editForm.get(['birthCountry'])!.value,
      birthCity: this.editForm.get(['birthCity'])!.value,
      photoContentType: this.editForm.get(['photoContentType'])!.value,
      photo: this.editForm.get(['photo'])!.value,
      teamId: this.editForm.get(['teamId'])!.value,
      parentId: this.editForm.get(['parentId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChildren>>): void {
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
