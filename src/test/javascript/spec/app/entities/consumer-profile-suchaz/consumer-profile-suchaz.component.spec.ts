/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ConsumerProfileSuchazComponent } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz.component';
import { ConsumerProfileSuchazService } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz.service';
import { ConsumerProfileSuchaz } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz.model';

describe('Component Tests', () => {

    describe('ConsumerProfileSuchaz Management Component', () => {
        let comp: ConsumerProfileSuchazComponent;
        let fixture: ComponentFixture<ConsumerProfileSuchazComponent>;
        let service: ConsumerProfileSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ConsumerProfileSuchazComponent],
                providers: [
                    ConsumerProfileSuchazService
                ]
            })
            .overrideTemplate(ConsumerProfileSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsumerProfileSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsumerProfileSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ConsumerProfileSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.consumerProfiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
