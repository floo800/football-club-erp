import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FootballClubErpTestModule } from '../../../test.module';
import { ChildrenUpdateComponent } from 'app/entities/children/children-update.component';
import { ChildrenService } from 'app/entities/children/children.service';
import { Children } from 'app/shared/model/children.model';

describe('Component Tests', () => {
  describe('Children Management Update Component', () => {
    let comp: ChildrenUpdateComponent;
    let fixture: ComponentFixture<ChildrenUpdateComponent>;
    let service: ChildrenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FootballClubErpTestModule],
        declarations: [ChildrenUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ChildrenUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChildrenUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChildrenService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Children(123);
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
        const entity = new Children();
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
