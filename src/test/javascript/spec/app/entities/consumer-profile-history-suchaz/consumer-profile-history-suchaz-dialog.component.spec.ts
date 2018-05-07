/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ConsumerProfileHistorySuchazDialogComponent } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz-dialog.component';
import { ConsumerProfileHistorySuchazService } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz.service';
import { ConsumerProfileHistorySuchaz } from '../../../../../../main/webapp/app/entities/consumer-profile-history-suchaz/consumer-profile-history-suchaz.model';

describe('Component Tests', () => {

    describe('ConsumerProfileHistorySuchaz Management Dialog Component', () => {
        let comp: ConsumerProfileHistorySuchazDialogComponent;
        let fixture: ComponentFixture<ConsumerProfileHistorySuchazDialogComponent>;
        let service: ConsumerProfileHistorySuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ConsumerProfileHistorySuchazDialogComponent],
                providers: [
                    ConsumerProfileHistorySuchazService
                ]
            })
            .overrideTemplate(ConsumerProfileHistorySuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsumerProfileHistorySuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsumerProfileHistorySuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ConsumerProfileHistorySuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.consumerProfileHistory = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'consumerProfileHistoryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ConsumerProfileHistorySuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.consumerProfileHistory = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'consumerProfileHistoryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
