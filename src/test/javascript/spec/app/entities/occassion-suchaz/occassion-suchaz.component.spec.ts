/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { OccassionSuchazComponent } from '../../../../../../main/webapp/app/entities/occassion-suchaz/occassion-suchaz.component';
import { OccassionSuchazService } from '../../../../../../main/webapp/app/entities/occassion-suchaz/occassion-suchaz.service';
import { OccassionSuchaz } from '../../../../../../main/webapp/app/entities/occassion-suchaz/occassion-suchaz.model';

describe('Component Tests', () => {

    describe('OccassionSuchaz Management Component', () => {
        let comp: OccassionSuchazComponent;
        let fixture: ComponentFixture<OccassionSuchazComponent>;
        let service: OccassionSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [OccassionSuchazComponent],
                providers: [
                    OccassionSuchazService
                ]
            })
            .overrideTemplate(OccassionSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OccassionSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OccassionSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new OccassionSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.occassions[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
