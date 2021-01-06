import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FootballClubErpTestModule } from '../../../test.module';
import { ChildrenComponent } from 'app/entities/children/children.component';
import { ChildrenService } from 'app/entities/children/children.service';
import { Children } from 'app/shared/model/children.model';

describe('Component Tests', () => {
  describe('Children Management Component', () => {
    let comp: ChildrenComponent;
    let fixture: ComponentFixture<ChildrenComponent>;
    let service: ChildrenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FootballClubErpTestModule],
        declarations: [ChildrenComponent],
      })
        .overrideTemplate(ChildrenComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChildrenComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChildrenService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Children(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.children && comp.children[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
