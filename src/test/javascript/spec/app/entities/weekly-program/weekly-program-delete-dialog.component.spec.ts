import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FootballClubErpTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { WeeklyProgramDeleteDialogComponent } from 'app/entities/weekly-program/weekly-program-delete-dialog.component';
import { WeeklyProgramService } from 'app/entities/weekly-program/weekly-program.service';

describe('Component Tests', () => {
  describe('WeeklyProgram Management Delete Component', () => {
    let comp: WeeklyProgramDeleteDialogComponent;
    let fixture: ComponentFixture<WeeklyProgramDeleteDialogComponent>;
    let service: WeeklyProgramService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FootballClubErpTestModule],
        declarations: [WeeklyProgramDeleteDialogComponent],
      })
        .overrideTemplate(WeeklyProgramDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WeeklyProgramDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WeeklyProgramService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
