/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { HobbyImageSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz-dialog.component';
import { HobbyImageSuchazService } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz.service';
import { HobbyImageSuchaz } from '../../../../../../main/webapp/app/entities/hobby-image-suchaz/hobby-image-suchaz.model';
import { HobbySuchazService } from '../../../../../../main/webapp/app/entities/hobby-suchaz';

describe('Component Tests', () => {

    describe('HobbyImageSuchaz Management Dialog Component', () => {
        let comp: HobbyImageSuchazDialogComponent;
        let fixture: ComponentFixture<HobbyImageSuchazDialogComponent>;
        let service: HobbyImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [HobbyImageSuchazDialogComponent],
                providers: [
                    HobbySuchazService,
                    HobbyImageSuchazService
                ]
            })
            .overrideTemplate(HobbyImageSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(HobbyImageSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HobbyImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new HobbyImageSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.hobbyImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'hobbyImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new HobbyImageSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.hobbyImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'hobbyImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
