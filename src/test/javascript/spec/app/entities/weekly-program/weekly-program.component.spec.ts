import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FootballClubErpTestModule } from '../../../test.module';
import { WeeklyProgramComponent } from 'app/entities/weekly-program/weekly-program.component';
import { WeeklyProgramService } from 'app/entities/weekly-program/weekly-program.service';
import { WeeklyProgram } from 'app/shared/model/weekly-program.model';

describe('Component Tests', () => {
  describe('WeeklyProgram Management Component', () => {
    let comp: WeeklyProgramComponent;
    let fixture: ComponentFixture<WeeklyProgramComponent>;
    let service: WeeklyProgramService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FootballClubErpTestModule],
        declarations: [WeeklyProgramComponent],
      })
        .overrideTemplate(WeeklyProgramComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WeeklyProgramComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WeeklyProgramService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WeeklyProgram(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.weeklyPrograms && comp.weeklyPrograms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
