import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FootballClubErpTestModule } from '../../../test.module';
import { WeeklyProgramDetailComponent } from 'app/entities/weekly-program/weekly-program-detail.component';
import { WeeklyProgram } from 'app/shared/model/weekly-program.model';

describe('Component Tests', () => {
  describe('WeeklyProgram Management Detail Component', () => {
    let comp: WeeklyProgramDetailComponent;
    let fixture: ComponentFixture<WeeklyProgramDetailComponent>;
    const route = ({ data: of({ weeklyProgram: new WeeklyProgram(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FootballClubErpTestModule],
        declarations: [WeeklyProgramDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WeeklyProgramDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WeeklyProgramDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load weeklyProgram on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.weeklyProgram).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
