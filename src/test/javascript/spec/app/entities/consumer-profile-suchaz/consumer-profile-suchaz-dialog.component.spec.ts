/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ConsumerProfileSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz-dialog.component';
import { ConsumerProfileSuchazService } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz.service';
import { ConsumerProfileSuchaz } from '../../../../../../main/webapp/app/entities/consumer-profile-suchaz/consumer-profile-suchaz.model';
import { SuchAzUserSuchazService } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz';

describe('Component Tests', () => {

    describe('ConsumerProfileSuchaz Management Dialog Component', () => {
        let comp: ConsumerProfileSuchazDialogComponent;
        let fixture: ComponentFixture<ConsumerProfileSuchazDialogComponent>;
        let service: ConsumerProfileSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ConsumerProfileSuchazDialogComponent],
                providers: [
                    SuchAzUserSuchazService,
                    ConsumerProfileSuchazService
                ]
            })
            .overrideTemplate(ConsumerProfileSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsumerProfileSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsumerProfileSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ConsumerProfileSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.consumerProfile = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'consumerProfileListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ConsumerProfileSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.consumerProfile = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'consumerProfileListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
