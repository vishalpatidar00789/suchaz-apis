/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ConsumerProfileHistorySuchazComponent } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz.component';
import { ConsumerProfileHistorySuchazService } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz.service';
import { ConsumerProfileHistorySuchaz } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz.model';

describe('Component Tests', () => {

    describe('ConsumerProfileHistorySuchaz Management Component', () => {
        let comp: ConsumerProfileHistorySuchazComponent;
        let fixture: ComponentFixture<ConsumerProfileHistorySuchazComponent>;
        let service: ConsumerProfileHistorySuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ConsumerProfileHistorySuchazComponent],
                providers: [
                    ConsumerProfileHistorySuchazService
                ]
            })
            .overrideTemplate(ConsumerProfileHistorySuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsumerProfileHistorySuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsumerProfileHistorySuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ConsumerProfileHistorySuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.consumerProfileHistories[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
