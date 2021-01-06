import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FootballClubErpTestModule } from '../../../test.module';
import { WeeklyProgramUpdateComponent } from 'app/entities/weekly-program/weekly-program-update.component';
import { WeeklyProgramService } from 'app/entities/weekly-program/weekly-program.service';
import { WeeklyProgram } from 'app/shared/model/weekly-program.model';

describe('Component Tests', () => {
  describe('WeeklyProgram Management Update Component', () => {
    let comp: WeeklyProgramUpdateComponent;
    let fixture: ComponentFixture<WeeklyProgramUpdateComponent>;
    let service: WeeklyProgramService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FootballClubErpTestModule],
        declarations: [WeeklyProgramUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(WeeklyProgramUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WeeklyProgramUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WeeklyProgramService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WeeklyProgram(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new WeeklyProgram();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
