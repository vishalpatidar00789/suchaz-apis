/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { TraitSuchazComponent } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz.component';
import { TraitSuchazService } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz.service';
import { TraitSuchaz } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz.model';

describe('Component Tests', () => {

    describe('TraitSuchaz Management Component', () => {
        let comp: TraitSuchazComponent;
        let fixture: ComponentFixture<TraitSuchazComponent>;
        let service: TraitSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [TraitSuchazComponent],
                providers: [
                    TraitSuchazService
                ]
            })
            .overrideTemplate(TraitSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TraitSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TraitSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TraitSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.traits[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
